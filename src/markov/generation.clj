(ns markov.generation
  (:require [clojure.string :as string]))

(defrecord Generation-String [current-string last-n-gram])

(defn cumulative-freq-map [letter-freqs]
  (reductions (fn [[_ csum] [key freq]] [key (+ csum freq)]) letter-freqs))

(defn freq-sum [c-freq-map] (last (last c-freq-map)))

(defn get-weighted-char [c-freq-map]
  (let [roll (rand-int (freq-sum c-freq-map))]
    (first (first (filter #(> (last %) roll) c-freq-map)))))

(defn append-generated-char [freq-maps gen-string]
  (if (contains? freq-maps (:last-n-gram gen-string))
    (let [c-freq-map (cumulative-freq-map (get freq-maps (:last-n-gram gen-string)))
          new-char (get-weighted-char c-freq-map)
          appended-string (str (:current-string gen-string) new-char)
          new-last-n-gram (str (string/join (rest (:last-n-gram gen-string))) new-char)]
      (Generation-String. appended-string new-last-n-gram))
    (Generation-String. (str (:current-string gen-string) " ") (first (keys freq-maps)))))

(defn run-generation [freq-maps length]
  (let [init-string (Generation-String. "" (first (keys freq-maps)))]
    (nth (iterate #(append-generated-char freq-maps %) init-string) length)))
