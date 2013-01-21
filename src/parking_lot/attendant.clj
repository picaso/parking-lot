(ns parking-lot.attendant
  (:use parking-lot.core)
  (:gen-class))

(defn can-attendant-park? [attendant]
  (or 
    (and (old-enough? attendant) 
         (not (lot-full? (-> attendant :lot)))) 
    (and (not (old-enough? attendant)) 
         (less-than-80-percent? (-> attendant :lot))))
  )

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

