(ns noughts-crosses.core-test
  (:require [clojure.test :refer :all]
            [noughts-crosses.core :refer :all]))

(deftest player-test
  (testing "Only X's and O's allowed"
    (let [grid [:- :-
                :- :-]]
      (is (= [:X :-
              :- :-] (move grid 0 :X)))
      (is (= [:O :-
              :- :-] (move grid 0 :O)))
      (is (thrown? AssertionError (move grid 0 :Y))))))

(deftest horizontal-win-test
  (testing "Play game and check X wins and O loses"
    (let [grid
          (-> [:- :- :-
               :- :- :-
               :- :- :-]
              (move 0 :X)
              (move 1 :X)
              (move 2 :X))]
      (is (= [:X :X :X
              :- :- :-
              :- :- :-] grid))
      (is (true? (winner? grid :X)))
      (is (false? (winner? grid :O))))))


(deftest vertical-win-test
  (testing "Play game and check X wins and O loses"
    (let [grid
          (-> [:- :- :-
               :- :- :-
               :- :- :-]
              (move 1 :X)
              (move 4 :X)
              (move 7 :X))]
      (is (= [:- :X :-
              :- :X :-
              :- :X :-] grid))
      (is (true? (winner? grid :X)))
      (is (false? (winner? grid :O))))))


(deftest diagonal-win-lr-test
  (testing "Play game and check X wins and O loses"
    (let [grid
          (-> [:- :- :-
               :- :- :-
               :- :- :-]
              (move 0 :X)
              (move 4 :X)
              (move 8 :X))]
      (is (= [:X :- :-
              :- :X :-
              :- :- :X] grid))
      (is (true? (winner? grid :X)))
      (is (false? (winner? grid :O))))))

(deftest diagonal-win-rl-test
  (testing "Play game and check X wins and O loses"
    (let [grid
          (-> [:- :- :-
               :- :- :-
               :- :- :-]
              (move 2 :X)
              (move 4 :X)
              (move 6 :X))]
      (is (= [:- :- :X
              :- :X :-
              :X :- :-] grid))
      (is (true? (winner? grid :X)))
      (is (false? (winner? grid :O))))))

(deftest move-illegal-positions-test
  (testing "Check illegal moves are rejected"
    (let [grid
          (-> [:- :- :-
               :- :- :-
               :- :- :-]
              (move 0 :X)
              (move 1 :X)
              (move 2 :X))]
      (is (thrown? AssertionError (move grid 2 :X)))
      (is (thrown? AssertionError (move grid -1 :X)))
      (is (thrown? AssertionError (move grid 9 :X))))))

