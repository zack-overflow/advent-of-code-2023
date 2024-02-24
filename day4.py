def main(input_file):
    # set counter to store amount of winning cards
    # we do this all together because
    total_pts = 0
    with open(input_file, 'r') as file:
        for line in file:
            total_pts += process_line(line)

    return total_pts

def process_line(line):
    data = line.split(": ")[1]
    data = data.split(" | ")
    winners = clean_numbers(data[0])
    our_numbers = clean_numbers(data[1])
    return process_card(winners, our_numbers)

def clean_numbers(line):
  nums = line.split()
  for i in range(len(nums)):
    nums[i] = int(nums[i])

  return nums
  
def process_card(winners, our_nums):
  total_winners = 0
  for winner in winners:
    if winner in our_nums:
      total_winners += 1

  return 0 if total_winners == 0 else 2 ** (total_winners - 1)

print(main("inputs/testinput4.1"))
print(main("inputs/input4"))

