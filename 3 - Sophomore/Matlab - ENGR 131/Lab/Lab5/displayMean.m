% Jacob Alpsaw
% jaa134

function [ avg ] = displayMean( data )
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

avg = mean(data);
plot(1:length(data), data, 'bo-');
hold on
plot([1 length(data)], [avg avg], 'r');
legend('data', 'mean');
ylabel('Value');
xlabel('Index');
end

