(ns clanhr.i18n.core-test
  (:require [clojure.test :refer :all]
            [clanhr.i18n.core :as i18n]))

(deftest smoke-test
  (is (= "test" (i18n/t :pt "test"))))
