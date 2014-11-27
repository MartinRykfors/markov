(ns markov.core-test
  (:require [clojure.test :refer :all]
            [markov.core :refer :all]))

(deftest n-gram
  (testing "n-grams is empty if word is shorter than n"
    (is (= [] (n-grams 4 "a"))))
  (testing "is same if n is equal to length of word"
    (is (= [ "aoeu"  ](n-grams 4 "aoeu"))))
  (testing "actually does what it is supposed to"
    (is (= ["ao" "oe" "eu"] (n-grams 2 "aoeu")))))
