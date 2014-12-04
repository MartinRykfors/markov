(ns markov.core
  (:require [markov.analysis :as analysis]
            [markov.generation :as generation]
            [clojure.string :as string])
  (:gen-class :main true)
  (:import [markov.generation Generation-String]))

(defn print-generated-string [freq-map length]
  (println (:current-string (generation/run-generation freq-map length))))

(defn clean-text [text]
  (string/replace text #"\n" " "))

(defn run-markov [source order]
  (let [source-text (clean-text (slurp source))]
    (let [freq-map (analysis/freq-map (inc order) source-text)]
      (print-generated-string freq-map 100))))

(defn -main
  [& args]
  (if (not (= 2 (count args)))
    (println "usage: source-path order")
    (let [[source order] args]
      (run-markov source order))))
