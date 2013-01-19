(ns parking-lot.core 
  (:gen-class))

(declare can-attendant-park?)

(defprotocol Park
  (park [this car]))

(defrecord Lot [size alot])

(def eighty-percent 4/5)

(defn old-enough? [user]
  (>(-> user :age) 16))

(defn lot-full? [lot]
  (=(count (-> lot :alot )) (-> lot :size)))

(defn less-than-80-percent? [lot]
  (< 
   (/ (count (-> lot :alot )) (-> lot :size))
   eighty-percent))

(defrecord Attendant [age lot]
  Park
  (park [this car] 
        (if (can-attendant-park? this) 
        (update-in  this [:lot :alot] conj :car)
          :lot-full-can't-park ) ))

(defn can-attendant-park? [attendant & args]
  (if (instance? Attendant attendant)
    (or 
     (and (old-enough? attendant) 
          (not (lot-full? (-> attendant :lot)))) 
     (and (not (old-enough? attendant)) 
          (less-than-80-percent? (-> attendant :lot))))
    (not (lot-full? args))))



