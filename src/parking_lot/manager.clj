(ns parking-lot.manager
	(:use parking-lot.core))

(defn- available-lots
  "Get the lots that are not full"
  [lot]
  (not (lot-full? lot))
  )

(defn get-available-parking-lot 
  "Should get next available parking lot"
  [manager]
  (first (filter  		 
  			available-lots (manager :lots)) 
  		)
 )

