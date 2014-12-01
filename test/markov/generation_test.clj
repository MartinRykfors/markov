(ns markov.generation-test
  (:require [clojure.test :refer :all]
            [markov.generation :refer :all])
  (:import [markov.generation Generation-String]))

(def simple-map {"a" {"b" 1}})
(def simple-seed (Generation-String. "initial " "a"))
(def lacking-seed (Generation-String. "q" "q"))

(deftest generation-test
  (testing "simple generation"
    (is (= "initial b" (:current-string (append-generated-char simple-map simple-seed))))
    (is (= "b" (:last-n-gram (append-generated-char simple-map simple-seed)))))
  (testing "falls back to existing n-gram if map lacks key"
    (is (= "q " (:current-string (append-generated-char simple-map lacking-seed))))
    (is (= "a" (:last-n-gram (append-generated-char simple-map lacking-seed))))))
