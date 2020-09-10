num="10011010"

sp=as.numeric(strsplit(num, split="")[[1]])

code=c()
count=1
ind=1
check=2
while(ind<=length(sp)){
  if(count==1 | count==2){
    code=c(code, NA)
  }else if(check*2==count){
    check=check*2
    code=c(code,NA)
  }else{
    code=c(code, sp[ind])
    ind=ind+1
  }
  count=count+1
}

slots=which(is.na(code))
count=1
while(count<=length(slots)){
  s=slots[count]
  val=checker(code, s)
  code[s]=val
  count=count+1
}
code


########################checking correctness
#correct: 011100101010

tocheck="011100101110"


stc=as.numeric(strsplit(tocheck, split="")[[1]])

#check parity bits
count=1
incorrect=c()
while(count<=length(stc)){
  curval=stc[count]
  par=checker(stc, count)
  if(curval==1){ #i.e. if curval is incorrectly influencing par
    if(par==1){par=0}else{par=1}
  }
  if(par!=curval){
    incorrect=c(incorrect, count)
  }
  count=count*2
}
wrongind=sum(incorrect)

if(stc[wrongind]==1){stc[wrongind]=0}else{stc[wrongind]=1}
stc

########################################functions
checker=function(vals, checknum){
  l=length(vals)
  if(checknum==1){
    checkinds=seq(from=1, to=l, by=2)
  }else{
    checkinds=c()
    count=0
    while(checknum+count<=l & count<checknum){
      theseinds=seq(from=checknum+count, to=l, by=2*checknum)
      checkinds=c(checkinds,theseinds)
      count=count+1
    }
  }
  checkinds=unique(sort(checkinds))
  p=sum(vals[checkinds], na.rm = T)
  if(p %% 2 == 0){
    return(0)
  }else{
    return(1)
  }
}








