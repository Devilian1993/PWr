(* Zadanie 1*)
let fiddle22 (tuple1, tuple2) = (((snd tuple2), (fst tuple1)), ((snd tuple1), (fst tuple2)));; 

fiddle22 ((1, 2), (3, 4));;

(*Zadanie 2*)
let hits (list1, list2) =
  let rec checker (list1, list2, index) =
    if list1 = [] || list2 = [] then []
    else
      if (List.hd list1) = (List.hd list2)
      then index :: checker (List.tl list1, List.tl list2, index + 1)
      else checker (List.tl list1, List.tl list2, index + 1)
  in
  checker (list1, list2, 0)
;;

hits ([1;2;3;4], [1;3;4;4]);;

(*Zadanie 3*)
let rec insert (list, element) =
  if list = [] then [(element, 1)]
  else 
    let pair = List.hd list in
    if (fst pair) = element
    then (element, snd pair + 1) :: List.tl list
    else pair :: insert (list, element)
  ;;

let list1 = insert ([], 10);;
let list2 = insert ([("q", 10);("b", 15)], "q");;

(* Zadanie 4*)
let theVeryNextDay (day, month, year) =
  let leapYear year = 
    (year mod 4 = 0 && year mod 100 <> 0) || year mod 400 = 0
  in
  let days_in_month year month =
    if month = 1 || month = 3 || month = 5 || month = 7 || month = 8 || month = 10 || month = 12 then 31
    else if month = 4 || month = 6 || month = 9 || month = 11 then 30
    else if month = 2 then (if leapYear year then 29 else 28)
    else 0
  in
  let checkDay day month year = day > 0 && day <= days_in_month year month in
  let checkMonth month = month > 0 && month <= 12 in
  let checkYear year = year > 0 in

  if not (checkDay day month year && checkMonth month && checkYear year) then
    raise (Failure "Nieprawidłowa data!")
  else if day = days_in_month year month then
    if month = 12 then (1, 1, year + 1)
    else (1, month + 1, year)
  else
    (day + 1, month, year)
;;

theVeryNextDay (28, 2, 2024);;
theVeryNextDay (31, 12, 2024);;
theVeryNextDay (32, 1, 2023);;

 
