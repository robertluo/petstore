# petstore

Demostration application for Lacinia (a Clojure GraphQL library).

## Installation

Download from https://github.com/robertluo/petstore

## Usage

Run the project directly, via `:main-opts` (`-m robertluo.petstore`):

    $ clojure -M:run-m
    Hello, World!

Run the project's tests (they'll fail until you edit them):

    $ clojure -X:test

Build an uberjar:

    $ clojure -X:uberjar

This will update the generated `pom.xml` file to keep the dependencies synchronized with
your `deps.edn` file. You can update the version (and SCM tag) information in the `pom.xml` using the
`:version` argument:

    $ clojure -X:uberjar :version '"1.2.3"'

If you don't want the `pom.xml` file in your project, you can remove it, but you will
also need to remove `:sync-pom true` from the `deps.edn` file (in the `:exec-args` for `depstar`).

Run that uberjar:

    $ java -jar petstore.jar

## License

Copyright © 2021 Tianluo

Distributed under the Eclipse Public License version 1.0.
