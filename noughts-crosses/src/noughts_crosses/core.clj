(ns noughts-crosses.core
  (:gen-class))

(defn move
  [grid pos plyr]
  {:pre [(<= 0 pos 2), (= :- (nth grid pos))]}
    (assoc grid pos plyr))

(defn winner?
  [grid plyr]
  (= (count grid) (count (filter #(= % plyr) grid))))


