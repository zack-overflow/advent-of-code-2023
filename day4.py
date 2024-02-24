def main(input_file):
    # set counter to store amount of winning cards
    # we do this all together because
    with open(input_file, 'r') as file:
        for line in file:
            process_line(line)

def process_line(line):
    data = line.split(": ")[1]
    data = data.split(" | ")
    winners = clean_numbers(data[0])
    our_numbers = clean_numbers(data[1])
    print(winners, "***", our_numbers)

def clean_numbers(line):
  nums = line.split()
  for i in range(len(nums)):
    nums[i] = int(nums[i])

  return nums
  

main("inputs/testinput4.1")
