def andOperator1
  if true && true
    puts ""
  end
end

def andOperator2
  if true and true
    puts ""
  end
end

def orOperator1
  if true || true
    puts ""
  end
end

def orOperator2
  if true or true
    puts ""
  end
end

def ternaryOperator
  a = 10
  b = a == 1 ? 20 : 30
end

def isEqualOperator
  if true == true
    puts ""
  end
end

def isNotEqualOperator
  if true != true
    puts ""
  end
end

def isEqualTypeOperator
  if Boolean === true
    puts ""
  end
end

def initializeVariableOperator
  x = 0
end