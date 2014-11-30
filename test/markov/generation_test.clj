(ns markov.generation-test
  (:require [clojure.test :refer :all]
            [markov.generation :refer :all])
  (:import [markov.generation Generation-String]))

(def simple-map {"a" {"b" 1}})
(def simple-seed (Generation-String. "initial " "a"))

(deftest generation-test
  (testing "simple generation"
    (is (= "initial b" (:current-string (append-generated-char simple-seed simple-map))))
    (is (= "b" (:last-n-gram (append-generated-char simple-seed simple-map))))))
