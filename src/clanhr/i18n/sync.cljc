(ns clanhr.i18n.sync
  "Generates the edn files needed on the clojure side"
  (:require [cheshire.core :refer :all]))

(def required-keys [:absence-vacations
                    :absence-family])

(defn- sync-locale
  "Syncs a specific locale"
  [lang file]
  (let [out-file (str "resources/" (name lang) ".edn")]
    (-> (slurp file)
        (parse-string true)
        (select-keys required-keys)
        (->> (map (fn [[k v]] [k (get v :message)]))
             (into {})
             (spit out-file)))
    (println "Wrote" out-file)))

(defn sync-data
  "Syncs data"
  []
  (sync-locale :pt "../frontend/js/locales/PT.json")
  (sync-locale :en "../frontend/js/locales/EN.json")
  (sync-locale :es "../frontend/js/locales/ES.json")
  (shutdown-agents))
