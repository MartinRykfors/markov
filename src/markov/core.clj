(ns markov.core
  (:require [clojure.string :as string]))

(defn mth-n-gram [m n word] (->> word (drop m) (take n)))

(defn n-grams [n word]
  (if (> n (count word))
    []
    (let [num-n-grams (-> (count word) (- n) (+ 1))]
      (map #(string/join (mth-n-gram % n word)) (range num-n-grams)))))
