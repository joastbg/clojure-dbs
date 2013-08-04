(ns testing.dbs
	(:require [monger.core :as mg])
	(:require [monger.collection :as mc])
	(:require [clojure.java.jdbc :as j])
	(:require [clojure.java.jdbc.sql :as s])
	(:import [com.mongodb MongoOptions ServerAddress]))

;; MongoDB
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

;; PostgreSQL
(let [db-host "localhost"
      db-port 5432
      db-name "mydb"]

(def db {:classname "org.postgresql.Driver" ; must be in classpath
           :subprotocol "postgresql"
           :subname (str "//" db-host ":" db-port "/" db-name)
           ; Any additional keys are passed to the driver
           ; as driver-specific properties.
           :user "postgres"
           :password "windows311"}))

(defn postgresql-insert []
	(j/insert! db :persons 
		{:name "Johan" :city "Lund"}))

(defn postgresql-find []
	(j/query db 
		(s/select * :persons (s/where {:city "Lund"}))))

(defn use-postgresql []
	(do
		(println "- PostgreSQL")
		(postgresql-insert)
		(postgresql-find)))

;; Main
(defn -main [& args]  
  (println "-- Testing various databases using Clojure")
  (use-mongodb)
  (use-postgresql))