(ns noughts-crosses.core
  (:require [clojure.spec.alpha :as s]
            [clojure.math.numeric-tower :as math]
            [noughts-crosses.specs :as specs])
  (:gen-class))

(defn move
  [grid pos player]
  {:pre [(s/valid? ::specs/grid grid)
         (s/valid? ::specs/player player)
         (<= 0 pos (- (count grid) 1))
         (= :- (nth grid pos))]}
  (assoc grid pos player))


(defn- winner-horizontal?
  [grid player]
  (as-> grid g
        (partition-all (math/sqrt (count g)) g)
        (map #(every? #{player} %) g)                    ;
        (some true? g)
        (true? g)))

(defn- winner-vertical?
  [grid player]
  false)

(defn- winner-diagonal?
  [grid player]
  false)

(defn winner?
  [grid player]
  (or (winner-horizontal? grid player)
      (winner-vertical? grid player)
      (winner-diagonal? grid player)))


