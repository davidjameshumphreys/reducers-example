# reducers-presentation

A simple example showing how to use the new reducers

## Usage

Run the code and look at the t/show-calls to see the traces.

Some example output:

    reducers-presentation.average> (amean [5 6 4 31])
    23/2
    reducers-presentation.average> (t/show-calls)
    {"ForkJoinPool-1-worker-19"
     ["arithmetic combine []"
      "arithmetic reduce {:total 0, :size 0},5"
      "arithmetic reduce {:total 5, :size 1},6"
      "arithmetic combine []"
      "arithmetic reduce {:total 0, :size 0},4"
      "arithmetic reduce {:total 4, :size 1},31"
      "arithmetic combine [2]{:total 11, :size 2},{:total 35, :size 2}"]}
    nil

## License

Copyright © 2012 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
