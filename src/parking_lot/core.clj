(ns parking-lot.core 
  (:gen-class))

(declare can-attendant-park?)

(defprotocol Park
  (park [this car])
  (remove-car [this car]))

(def eighty-percent 4/5)

(defn- old-enough? [user]
  (>(-> user :age) 16))


(defn car-found? [lot car]
  (not (zero? (count 
        (filter #(= % car)  lot )))))

(defn rm-car [lot car]
  (filter #(not (= % car)) lot))


(defn- less-than-80-percent? [lot]
  (< 
   (/ (count (-> lot :alot )) (-> lot :size))
   eighty-percent))

(defn lot-full? [lot]
  (=(count (-> lot :alot )) (-> lot :size)))

(defrecord Lot [size alot])

(defrecord Attendant [age lot]
  Park
  (park [this car] 
        (if (can-attendant-park? this) 
          (update-in  this [:lot :alot] conj :car)
            :lot-full-can't-park ))
  (remove-car [this car]
        (if (car-found? (-> lot :alot) car)
          (update-in this [:lot :alot] rm-car car)
            :no-car-found )))

(defn can-attendant-park? [attendant & args]
  (if (instance? Attendant attendant)
    (or 
     (and (old-enough? attendant) 
          (not (lot-full? (-> attendant :lot)))) 
     (and (not (old-enough? attendant)) 
          (less-than-80-percent? (-> attendant :lot))))
    (not (lot-full? args))))



