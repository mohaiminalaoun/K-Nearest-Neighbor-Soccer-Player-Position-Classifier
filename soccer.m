
%% Initialization
clear ; close all; clc

%% Load Data

data = load('data.txt');





m = size(data)(1) % 50 rows
classified_X = zeros([10,1]);


%Normalize goals and apps
max1 = max(data(:,1));
min1 = min(data(:,1));
for i=1:m
  data(i,1) = (data(i,1)-min1)/(max1-min1);
endfor

max2 = max(data(:,2));
min2 = min(data(:,2));
for i=1:m
  data(i,2) = (data(i,2)-min2)/(max2-min2);
endfor

clusters = [data(10,:);
            data(20,:);
            data(30,:);
            data(40,:);
            data(50,:)
            ];
k = 5; %want 5 clusters


%centroids initialized to random e.g. 10,20,30,40,50

for i=1:50
  currentPoint = data(i,:);
  fprintf("currentPoint is ");
  disp(currentPoint);
  fprintf("\n");
  maxDist = realmax;
  ans = 1;
  for j =1:1:k
   dist1 = currentPoint - clusters(j,:);
   dist1 = dist1.^2;
   dist = dist1 * [1;1;1];
   disp(dist);
   if dist<maxDist
     maxDist = dist;
     fprintf("maxDist changed to %f \n",maxDist);
     
     ans = j;
     fprintf("ans is %f \n",ans);
     classified_X(i)=ans;
    endif
  endfor
  %fprintf("for %f, ans is %f \n",i,ans)
  
endfor
