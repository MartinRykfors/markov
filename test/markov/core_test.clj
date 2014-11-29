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

(deftest frequency-map-tests
  (testing "add new n-gram creates new entry"
    (is (= {"ao" {"u" 1}} (update-freq-map {} "aou" )))
    (is (= {"aoe" {"u" 1}} (update-freq-map {} "aoeu" )))
    (is (= {"snt" {"h" 1} "aoe" {"u" 1}} (form-freq-map ["aoeu" "snth"])))
    (is (= {"snt" {"h" 2} "aoe" {"u" 1}} (form-freq-map ["aoeu" "snth" "snth"])))))

