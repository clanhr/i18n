(ns clanhr.i18n.core
  "Utilities for i18n"
  (:require [clanhr.i18n.pt :as pt]
            [clanhr.i18n.en :as en]
            [clanhr.i18n.es :as es]))

(def config
  {:dictionary {:pt pt/data
                :en en/data
                :es es/data}
   :fallback-locale :en})

(defn t
  "Translates tokens given a language"
  ([lang token]
   (t lang token nil))
  ([lang token default-text]
   (when token
     (or (get-in config [:dictionary (keyword lang) (keyword token)])
         default-text
         (get-in config [:dictionary (:fallback-locale config) (keyword token)])
         (str "?" (name token) "?")))))
