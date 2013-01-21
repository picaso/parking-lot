(ns parking-lot.manager
  (:use parking-lot.core)
  (:gen-class))

(defn- available-lot
  "Get the lots that are not full"
  [lot]
  (not (lot-full? lot)))

(defn get-available-parking-lot 
  "Should get next available parking lot"
  [manager]
  (first (filter       
           available-lot (-> manager :lots)) ))

(defn can-attendant-park? [lot]
  (not (lot-full? lot))
)

(defrecord Manager [lots]
  Park
  (park [this car] 
        (let [alot (get-available-parking-lot this)]
          (if (can-attendant-park? alot) 
            (update-in  alot [:alot] conj :car)
            :lot-full-can't-park ) )))


