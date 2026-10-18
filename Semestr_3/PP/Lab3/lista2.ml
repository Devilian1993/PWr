let find list element = 
  let rec findIter list element index = 
    match list with
    | [] -> []
    | head :: tail ->
      match element = head with
      | true -> index :: findIter tail element (index+1)
      | false -> findIter tail element (index+1)
    in

    findIter list element 0



let find1232 = find [1;2;3;2];;
find1232 2

let rec splitNRec list n = 
  let rec generateEmpty n =
    match n with
    | 0 -> []
    | _ -> [] :: generateEmpty (n - 1)
  in

  let rec appendOnIndex list index item = 
    match list with
    | [] -> []
    | head :: tail -> 
      match index with
      | 0 -> (item :: head) :: tail
      | _ -> head :: appendOnIndex tail (index - 1) item
  in
  
  let rec helper list index =
    match list with
    | [] -> generateEmpty n
    | head :: tail ->
      let subLists = helper tail ((index + 1) mod n) in
      appendOnIndex subLists index head
  in
  
  helper list 0
;;

let splitNTail list n =
  let rec generateEmpty n =
    match n with
    | 0 -> []
    | _ -> [] :: generateEmpty (n - 1)
  in

  let rec appendOnIndex list index item = 
    match list with
    | [] -> []
    | head :: tail -> 
      match index with
      | 0 -> (item :: head) :: tail
      | _ -> head :: (appendOnIndex tail (index - 1) item)
  in
  
  let rec tail_helper list acc i =
    match list with
    | [] -> acc 
    | h :: t ->
      let next_acc = appendOnIndex acc i h in
      let next_i = (i + 1) mod n in
      
      tail_helper t next_acc next_i
  in
  
  let initial_acc = generateEmpty n in
  tail_helper list initial_acc 0
;;

splitNRec [1;2;3;4;5;6;7] 4;;
splitNTail [1;2;3;4;5] 3;;
  
