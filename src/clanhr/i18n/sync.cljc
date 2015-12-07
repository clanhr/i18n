(ns clanhr.i18n.sync
  "Generates the edn files needed on the clojure side"
  (:require [cheshire.core :refer :all]))

(def required-keys [:absence-vacations
                    :absence-maternity-leave
                    :absence-paternity-leave
                    :absence-death-license
                    :absence-marriage-license
                    :absence-medical
                    :absence-education
                    :absence-justified
                    :absence-unjustified
                    :absence-family
                    :name
                    :birth-date
                    :civil-state
                    :nationality
                    :email
                    :phone
                    :idcard
                    :address
                    :deficients
                    :dependents
                    :vat-number
                    :other-document
                    :income-ownership
                    :phone-country-number
                    :social-security-number
                    ])

(defn- generate-code-file
  "Generates a valid clj code file"
  [lang m]
  (str "(ns clanhr.i18n." (name lang) ")"
       "(def data " m ")"))

(defn- sync-locale
  "Syncs a specific locale"
  [lang file]
  (let [out-file (str "src/clanhr/i18n/" (name lang) ".clj")]
    (-> (slurp file)
        (parse-string true)
        (select-keys required-keys)
        (->> (map (fn [[k v]] [k (get v :message)]))
             (into {})
             (generate-code-file lang)
             (spit out-file)))
    (println "Wrote" out-file)))

(defn sync-data
  "Syncs data"
  []
  (sync-locale :pt "../frontend/js/locales/PT.json")
  (sync-locale :en "../frontend/js/locales/EN.json")
  (sync-locale :es "../frontend/js/locales/ES.json")
  (shutdown-agents))
