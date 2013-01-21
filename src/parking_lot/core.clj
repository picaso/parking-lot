(ns parking-lot.core 
  (:gen-class))

(defprotocol Park
  (park [this car])
  (remove-car [this car]))

(def eighty-percent 4/5)

(defn old-enough? [user]
  (>(-> user :age) 16))

(defn car-found? [lot car]
  (not (zero? (count 
                (filter #(= % car)  lot )))))

(defn rm-car [lot car]
  (filter #(not (= % car)) lot))

(defn less-than-80-percent? [lot]
  (< 
    (/ (count (-> lot :alot )) (-> lot :size))
    eighty-percent))

(defn lot-full? [lot]
  (=(count (-> lot :alot )) (-> lot :size)))

(defrecord Lot [size alot])
