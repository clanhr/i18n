(ns clanhr.i18n.sync
  "Generates the edn files needed on the clojure side"
  (:require [cheshire.core :refer :all]))


(def mapping
  {:abscence.type.vacations         :absence-vacations
   :abscence.type.maternity-leave   :absence-maternity-leave
   :abscence.type.paternity-leave   :absence-paternity-leave
   :abscence.type.death-license     :absence-death-license
   :abscence.type.marriage-license  :absence-marriage-license
   :abscence.type.medical           :absence-medical
   :abscence.type.education         :absence-education
   :abscence.type.justified         :absence-justified
   :abscence.type.unjustified       :absence-unjustified
   :abscence.type.family            :absence-family

   :academic.degree                   :degree
   :academic.degree.basic-education-1 :basic-education-1
   :academic.degree.basic-education-2 :basic-education-2
   :academic.degree.basic-education-3 :basic-education-3
   :academic.degree.high-school       :high-school
   :academic.degree.associates-degree :associates-degree
   :academic.degree.bachelors-degree  :bachelors-degree
   :academic.degree.masters-degree    :masters-degree
   :academic.degree.doctoral-degree   :doctoral-degree

   :pro.email       :professional-email
   :pro.telephone   :professional-phone
   :job.role        :job-position
   :manager         :approver

   :personal.email           :personal-email
   :personal.telephone       :personal-phone

   :fiscal.information.number.dependants :number-of-dependents

   :civil.status             :civil-status
   :civil.status.married     :married
   :civil.status.single      :single
   :civil.status.divorced    :divorced
   :civil.status.widowed     :widowed
   :civil.status.civil-union :civil-union

   :academic.course                         :school.school-course
   :cost.center                             :cost-center

   :fiscal.information.income.payee            :income-owership
   :fiscal.information.income.payee.1-titular  :1-titular
   :fiscal.information.income.payee.2-titulars :2-titulars

   :fiscal.information.handicap             :handicap
   :fiscal.information.handicap.declarant   :declarant
   :fiscal.information.handicap.spouse      :spouse
   :fiscal.information.handicap.dependent   :dependent
   :fiscal.information.handicap.not         :not
   :fiscal.information.handicap.undefined   :undefined

   :fiscal.information.number-of-dependents :number-of-dependents


   :days.left.holidays          :enjoyedDays
   :days.scheduled              :scheduledDays
   :days.off.counter.free.days  :freeDays

   :type.contract :contract-type
   :contract.list.contract.type.fixed-term                       :fixed-term
   :contract.list.contract.type.no-term                          :no-term
   :contract.list.contract.type.no-term-partial                  :no-term-partial
   :contract.list.contract.type.intermittent                     :intermittent
   :contract.list.contract.type.intermittent-partial             :intermittent-partial
   :contract.list.contract.type.fixed-term-partial               :fixed-term-partial
   :contract.list.contract.type.not-fixed-term                   :not-fixed-term
   :contract.list.contract.type.not-fixed-term-partial           :not-fixed-term-partial
   :contract.list.contract.type.very-short-duration              :very-short-duration
   :contract.list.contract.type.telework                         :telework
   :contract.list.contract.type.telework-partial                 :telework-partial
   :contract.list.contract.type.service-commission               :service-commission
   :contract.list.contract.type.service-commission-partial       :service-commission-partial
   :contract.list.contract.type.fixed-term-temporary             :fixed-term-temporary
   :contract.list.contract.type.fixed-term-temporary-partial     :fixed-term-temporary-partial
   :contract.list.contract.type.not-fixed-term-temporary         :not-fixed-term-temporary
   :contract.list.contract.type.not-fixed-term-temporary-partial :not-fixed-term-temporary-partial
   :contract.list.contract.type.unbounded-time-temporary         :unbounded-time-temporary
   :contract.list.contract.type.unbounded-time-temporary-partial :unbounded-time-temporary-partial
   :contract.list.contract.type.internship                       :internship
   :contract.list.contract.type.indefinite-duration-full-time    :indefinite-duration-full-time
   })

(def required-keys [
                    ;; Absence types
                    :abscence.type.vacations
                    :abscence.type.maternity-leave
                    :abscence.type.paternity-leave
                    :abscence.type.death-license
                    :abscence.type.marriage-license
                    :abscence.type.medical
                    :abscence.type.education
                    :abscence.type.justified
                    :abscence.type.unjustified
                    :abscence.type.family

                    :name
                    :birth-date
                    :linkedin
                    :facebook
                    :twitter
                    :hours
                    :days
                    :emergency-contact.contact-name
                    :emergency-contact.relationship
                    :emergency-contact.phone
                    :emergency-contact.phone-country-number
                    :about-me
                    :school.school-name
                    :school.school-degree
                    :school.school-course
                    :school.school-name
                    :school.course
                    :bank-account.bank-name
                    :bank-account.iban
                    :bank-account.swift
                    :fiscal-address.street
                    :fiscal-address.district
                    :fiscal-address.zip-code
                    :fiscal-address.city
                    :fiscal-address.parish
                    :fiscal-address.country-code
                    :fiscal-address.country-name
                    :address.street
                    :address.district
                    :address.zip-code
                    :address.city
                    :address.parish
                    :address.country-code
                    :address.country-name
                    :groups
                    :cost-centers
                    :total
                    :totalDays


                    :personal-data
                    :company-data
                    :personal.email
                    :pro.email
                    :pro.telephone
                    :fiscal.information.number.dependants
                    :personal.telephone
                    :cost.center

                    :salary
                    :expenses
                    :iht
                    :food-allowance
                    :salary-observations

                    :tags
                    :teams
                    :projects
                    :job.role
                    :position
                    :job-description
                    :manager
                    :approver-name
                    :approver-email

                    :section
                    :field
                    :old-value
                    :new-value

                    :deficients
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
                    :phone-country-code
                    :colaborator-id

                    ;; civil status
                    :civil.status
                    :civil.status.married
                    :civil.status.single
                    :civil.status.divorced
                    :civil.status.widowed
                    :civil.status.civil-union

                    ;; fiscal info
                    :fiscal.information.income.payee
                    :fiscal.information.income.payee.1-titular
                    :fiscal.information.income.payee.2-titulars

                    :fiscal.information.handicap
                    :fiscal.information.handicap.declarant
                    :fiscal.information.handicap.spouse
                    :fiscal.information.handicap.dependent
                    :fiscal.information.handicap.not
                    :fiscal.information.handicap.undefined

                    ;; Acadmics
                    :academic.course
                    :academic.degree
                    :academic.degree.basic-education-1
                    :academic.degree.basic-education-2
                    :academic.degree.basic-education-3
                    :academic.degree.high-school
                    :academic.degree.associates-degree
                    :academic.degree.bachelors-degree
                    :academic.degree.masters-degree
                    :academic.degree.doctoral-degree

                    ;; reports
                    :vacations-and-absences-excel-file
                    :vacations-balance-excel-file
                    :user-changes-excel-file
                    :users-excel-file
                    :type

                    :days.left.holidays
                    :days.scheduled
                    :days.off.counter.free.days
                    :total

                    :start-date
                    :end-date
                    :unit

                    ;;contracts

                    :type.contract :contract-type
                    :contract-start-date
                    :contract-end-date
                    :contract-observations

                    :contract.list.contract.type.fixed-term
                    :contract.list.contract.type.fixed-term-partial
                    :contract.list.contract.type.no-term
                    :contract.list.contract.type.no-term-partial
                    :contract.list.contract.type.intermittent
                    :contract.list.contract.type.intermittent-partial
                    :contract.list.contract.type.fixed-term-partial
                    :contract.list.contract.type.not-fixed-term
                    :contract.list.contract.type.not-fixed-term-partial
                    :contract.list.contract.type.very-short-duration
                    :contract.list.contract.type.telework
                    :contract.list.contract.type.telework-partial
                    :contract.list.contract.type.service-commission
                    :contract.list.contract.type.service-commission-partial
                    :contract.list.contract.type.fixed-term-temporary
                    :contract.list.contract.type.fixed-term-temporary-partial
                    :contract.list.contract.type.not-fixed-term-temporary
                    :contract.list.contract.type.not-fixed-term-temporary-partial
                    :contract.list.contract.type.unbounded-time-temporary
                    :contract.list.contract.type.unbounded-time-temporary-partial
                    :contract.list.contract.type.internship
                    :contract.list.contract.type.indefinite-duration-full-time

                    ])

(defn- generate-code-file
  "Generates a valid clj code file"
  [lang m]
  (str "(ns clanhr.i18n." (name lang) ")"
       "(def data " m ")"))

(defn- map-new->old-keys
  [k]
  (if-let [old (get mapping k)]
    old
    k))

(defn- sync-locale
  "Syncs a specific locale"
  [lang file]
  (let [out-file (str "src/clanhr/i18n/" (name lang) ".clj")]
    (-> (slurp file)
        (parse-string true)
        (select-keys required-keys)
        (->> (map (fn [[k v]]
                    [(map-new->old-keys k) v ]))
             (into {})
             (generate-code-file lang)
             (spit out-file)))
    (println "Wrote" out-file)))

(defn sync-data
  "Syncs data"
  []
  (sync-locale :pt "../frontend/app/src/locales/pt.json")
  (sync-locale :en "../frontend/app/src/locales/en.json")
  (sync-locale :es "../frontend/app/src/locales/es.json")
  (shutdown-agents))
