(ns clanhr.i18n.core-test
  (:require [clojure.test :refer :all]
            [clanhr.i18n.core :as i18n]))

(deftest smoke-test
  (testing "default"
    (is (= "Waza" (i18n/t "en" "asdasdasdadasdasds" "Waza"))))
  (testing "unknown"
    (is (= "?waza?" (i18n/t "en" "waza")))
    (is (= "?waza?" (i18n/t "en" :waza))))
  (testing "English language"
    (is (= "Vacations" (i18n/t "en" "absence-vacations")))
    (is (= "Vacations" (i18n/t "en" :absence-vacations)))
    (is (= "Vacations" (i18n/t :en "absence-vacations"))))
  (testing "Portuguese language"
    (is (= "Férias" (i18n/t "pt" "absence-vacations")))
    (is (= "Férias" (i18n/t "pt" :absence-vacations)))
    (is (= "Férias" (i18n/t :pt "absence-vacations"))))
  (testing "Spanish language"
    (is (= "Vacaciones" (i18n/t "es" "absence-vacations")))
    (is (= "Vacaciones" (i18n/t "es" :absence-vacations)))
    (is (= "Vacaciones" (i18n/t :es "absence-vacations")))))
