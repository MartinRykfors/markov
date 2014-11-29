(ns markov.core
  (:require [clojure.string :as string]))

(defn mth-n-gram [m n word] (->> word (drop m) (take n)))

(defn n-grams [n word]
  (if (> n (count word))
    []
    (let [num-n-grams (-> (count word) (- n) (+ 1))]
      (map #(string/join (mth-n-gram % n word)) (range num-n-grams)))))

(defn update-freq-map [f-map n-g]
  (let [[key letter] (map string/join (split-at (- (count n-g) 1) n-g))]
    (if (contains? (f-map key) letter)
      (update-in f-map [key letter] inc)
      (assoc-in f-map [key letter] 1))))

(defn form-freq-map [n-gs]
  (reduce update-freq-map {} n-gs))

(defn freq-map [order text] (form-freq-map (n-grams order text)))
