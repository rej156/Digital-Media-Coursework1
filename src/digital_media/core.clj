(ns digital-media.core
  (:use
   [twitter.oauth]
   [twitter.callbacks]
   [twitter.callbacks.handlers]
   [twitter.api.restful]
   [twitter.api.search])
  (:import
   (twitter.callbacks.protocols SyncSingleCallback)))

(def my-creds (make-oauth-creds "368DL0LWRIDRewN4kfsQFDYYD"
                                "nGiIOF5wJ6YGxWZSNFoQI4RPtXfM3IifYC0bsWLt50zSM38stZ"
                                "3072053125-9BRhUIBcJSTlpWNMCsWr0Bs1lWT8kUYAV0byY4M"
                                "Vqe6dpZj2nE0kGGRgR8XMaNfcUcH7LVgRMxO5WkOWVgaF"
                                ))

(def extract-body (SyncSingleCallback. response-return-body
                                       response-throw-error
                                       exception-rethrow))

(users-show :oauth-creds my-creds
            :callbacks extract-body
            :params {:screen-name "AdamJWynne"})
