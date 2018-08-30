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

(defn- player-at?
  [idxs grid player]
  (every? #(= player (nth grid %)) idxs))

(defn- winning-line?
  [grid player line-idxs]
  (->> line-idxs
       (map #(player-at? % grid player))
       (some true?)
       (true?)))

(defn- winner-horizontal?
  [grid player]
  (let [grid-width (grid-width grid)]
    (->> (range 0 (count grid) grid-width)
         (map #(range % (+ % grid-width)))
         (winning-line? grid player))))

(defn- winner-vertical?
  [grid player]
  (let [grid-width (grid-width grid)]
    (->> (range 0 grid-width)
         (map #(range % (count grid) grid-width))
         (winning-line? grid player))))

(defn- winner-diagonal?
  [grid player]
  false)

(defn winner?
  [grid player]
  (or (winner-horizontal? grid player)
      (winner-vertical? grid player)
      (winner-diagonal? grid player)))


