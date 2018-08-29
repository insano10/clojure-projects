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

(defn- grid-width
  [square-grid]
  (math/sqrt (count square-grid)))

(defn- winning-line?
  [idxs grid player]
  (every? #(= player (nth grid %)) idxs))

; refactor this to use idxs rather than grid elements then can reuse last 3 steps from vertical
(defn- winner-horizontal?
  [grid player]
  (let [grid-width (grid-width grid)]
    (->> (partition-all grid-width grid)
         (map #(every? #{player} %))
         (some true?)
         (true?))))

(defn- winner-vertical?
  [grid player]
  (let [grid-width (grid-width grid)]
    (->> (range 0 grid-width)
         (map #(range % (count grid) grid-width))
         (map #(winning-line? % grid player))
         (some true?)
         (true?))))

(defn- winner-diagonal?
  [grid player]
  false)

(defn winner?
  [grid player]
  (or (winner-horizontal? grid player)
      (winner-vertical? grid player)
      (winner-diagonal? grid player)))


