(ns testing.postgres	
	(:require [clojure.java.jdbc :as j])
	(:require [clojure.java.jdbc.sql :as s]))

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
           :password "secure-password"}))

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