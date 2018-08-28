(ns noughts-crosses.core-test
  (:require [clojure.test :refer :all]
            [noughts-crosses.core :refer :all]))

(deftest move-test
  (testing "Play game and check X wins and O loses"
    (let [grid
          (-> [:- :- :-]
              (move 0 :X)
              (move 1 :X)
              (move 2 :X))]
      (is (= [:X :X :X] grid))
      (is (true? (winner? grid :X)))
      (is (false? (winner? grid :O))))))

(deftest move-illegal-positions-test
  (testing "Check illegal moves are rejected"
    (let [grid
          (-> [:- :- :-]
              (move 0 :X)
              (move 1 :X)
              (move 2 :X))]
      (is (thrown? AssertionError (move grid 2 :X)))
      (is (thrown? AssertionError (move grid -1 :X)))
      (is (thrown? AssertionError (move grid 3 :X))))))
