(ns clanhr.i18n.core
  "Utilities for i18n"
  (:require [taoensso.tower :as tower]))

(def my-tconfig
  {:dictionary {:pt "pt.edn"
                :en "en.edn"
                :es "es.edn"}
   :fallback-locale :en})

(def tower-t (tower/make-t my-tconfig))

(def t
  "Translates tokens given a language"
  tower-t)
