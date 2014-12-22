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

(defn run-markov [source order length]
  (let [source-text (clean-text (slurp source))
        freq-map (analysis/freq-map (inc order) source-text)]
    (print-generated-string freq-map length)))

(defn -main
  [& args]
  (if (not (= 3 (count args)))
    (println "usage: source-path markov-order generated-text-length")
    (let [[source order length] args]
      (run-markov source (read-string order) (read-string length)))))
