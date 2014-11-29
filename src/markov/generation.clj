(ns markov.generation)

(defn cumulative-freq-map [letter-freqs]
  (reductions (fn [[_ csum] [key freq]] [key (+ csum freq)]) letter-freqs))

(defn freq-sum [c-freq-map] (last (last c-freq-map)))

(defn get-weighted-char [c-freq-map]
  (let [roll (rand-int (freq-sum c-freq-map))]
    (first (filter #(> (last %) roll) c-freq-map))))
