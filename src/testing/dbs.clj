(ns testing.dbs
	(:use testing.mongo)
	(:use testing.postgres)
	(:use testing.neo4j))

;; Main
(defn -main [& args]  
  (println "-- Testing various databases using Clojure")
  (use-mongodb)
  (use-postgresql)
  (use-neo4j))