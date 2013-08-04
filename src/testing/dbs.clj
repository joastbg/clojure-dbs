(ns testing.dbs
	(:require [monger.core :as mg])
	(:require [monger.collection :as mc])
	(:import [com.mongodb MongoOptions ServerAddress]))

(defn mongodb-init []
	(do
		(mg/connect!)
		(mg/set-db! (mg/get-db "monger-test")))
		(println "> Connected and db set."))

(defn mongodb-insert []
	(do 
		(mc/insert "documents" {:first_name "John"  :last_name "Lennon"})
		(mc/insert "documents" {:first_name "Ringo" :last_name "Starr"}))
		(println "> 2 documents inserted"))

(defn mongodb-find []
	(do
		(def res (mc/find-maps "documents" {:first_name "Ringo"}))
		(println "> Found documents:\n" res)))

(defn use-mongodb []
	(do
		(println "- MongoDB")
		(mongodb-init)
		(mongodb-insert)
		(mongodb-find)))

(defn -main [& args]  
  (println "-- Testing various databases using Clojure")
  (use-mongodb))