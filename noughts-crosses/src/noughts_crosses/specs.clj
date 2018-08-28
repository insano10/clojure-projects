(ns noughts-crosses.specs
  (:require [clojure.spec.alpha :as s])
  (:gen-class))

(s/def ::empty #{:-})
(s/def ::player #{:X :O})
(s/def ::grid-element (s/or :player ::player
                            :empty  ::empty))

;todo don't cheat here
(s/def ::square-number (s/and int? #{4 9 16 25 36 49}))

(s/def ::grid (s/coll-of ::grid-element :kind #(s/valid? ::square-number (count %))))



