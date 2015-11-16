(ns clanhr.i18n.core
  "Utilities for i18n"
  (:require [taoensso.tower :as tower]
            [clanhr.i18n.pt :as pt]
            [clanhr.i18n.en :as en]
            [clanhr.i18n.es :as es]))

(def config
  {:dictionary {:pt pt/data
                :en en/data
                :es es/data}
   :fallback-locale :en})

#_(def tower-t (tower/make-t config))

(defn t
  "Translates tokens given a language"
  [lang token]
  (or (get-in config [:dictionary (keyword lang) (keyword token)])
      (get-in config [:dictionary (:fallback-locale config) (keyword token)])))
