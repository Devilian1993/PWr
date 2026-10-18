let (?!) a = 
  fun x ->
    let rec helper a x x0 acc =
      match a with
      | [] -> acc
      | head :: tail -> 
          let pow = if x = x0 then 1.0 else if x0 > 1.0 then x /. x0 else x *. x0 in
          helper tail (x /. x0) x0 (acc +. pow*.head)
      in
    helper a x x 0.0
    ;;
    
let n1 = ?![1.0;1.0] 1.0;;
let n2 = ?![1.0;1.0;1.0;1.0;1.0;1.0;1.0;1.0] 2.;;         