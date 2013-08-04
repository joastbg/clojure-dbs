(ns testing.neo4j
	(:require [clojurewerkz.neocons.rest :as nr])
	(:require [clojurewerkz.neocons.rest.nodes :as nn]))

;; Neo4j
(defn neo4j-init []
	(do
		(nr/connect! "http://localhost:7474/db/data/")
		(println "> Connected and db set.")))

(defn neo4j-insert []
	(do 
		(let [node (nn/create {:name "Johan Astborg" :city "Lund"})]
		    (println node))
		(println "> 1 document(s) inserted")))

(comment
	;TODO: Fix null-pointer exception
(defn neo4j-get []
	(do
		(println (nn/get (:id 2)))))
)

(defn use-neo4j []
	(do
		(println "- Neo4j")
		(neo4j-init)
		(neo4j-insert)))
		;(neo4j-get)))