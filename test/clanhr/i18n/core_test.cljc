(ns clanhr.i18n.core-test
  (:require [clojure.test :refer :all]
            [clanhr.i18n.core :as i18n]))

(deftest smoke-test
  (is (= "Férias" (i18n/t :pt "absence-vacations"))))
