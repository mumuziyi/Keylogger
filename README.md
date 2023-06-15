# Keylogger

Keylogger主要用于模拟后台记录用户输入。
src.main.resources.GlobalKeyListener 是用来进行监视和记录用户的密码输入。程序这个类运行之后程序会自动记录用户的输入（用户每输入100个字符
写入test.txt文档一次），如果用户输入字符不足100个，则每10秒进行一次写入。

src.main.resources.PasswordAnalise 是用来对test.txt进行密码分析（这里使用递归进行暴力破解，因此效率极低）。暴力破解方式为一次遍历回车前n个
（n为自己设置的最大密码长度）字符。（测试中在PasswordCheck中设置正确密码）

可以直接讲这些类与其他应用进行结合，比如使用socket将记录下来的test.txt发送到远程，这样就可以监视用户输入。

下面为一个可能的使用实例

## 密码监听实验
### 根据输入情况：
简单情况，用户只输入密码
日常使用，不只输入密码
复杂情况： 在输入密码之后切换到其他页面随机敲击键盘几次，再返回当前页面enter


### 根据密码状况：
如果用户多次使用同一种密码，但是不是任何输入密码的时候都使用了软键盘
如果用户密码只有数字
用户密码有数字大小写字母以及符号

### 实验结果
用户输入时长变化
用户密码输入错误率
用户接受程度
密码破译难度

## 延伸
寻找相关密码破解方案论文，可以与肩窥相结合
根据实验得出一些增加密码破解难度的结论，比如使用多种密码形式组合。



# Keylogger
A keylogger is primarily used to simulate and record user inputs in the background.

The src.main.resources.GlobalKeyListener class is used for monitoring and recording users' password inputs. Once this class is running, the program will automatically record user inputs. If the user's input reaches 100 characters, it will be written to the "test.txt" document. If the input is less than 100 characters, it will be written every 10 seconds.

The src.main.resources.PasswordAnalise class is used for password analysis of the "test.txt" file. It uses recursive brute force to crack passwords, making it highly inefficient. The brute force method involves iterating through the first n characters before hitting the Enter key (where n is the maximum password length you set). (In testing, the correct password is set in PasswordCheck).

These classes can be integrated with other applications, such as using sockets to send the recorded "test.txt" file to a remote location, enabling monitoring of user inputs.

Below is a possible use case:

## Password Monitoring Experiment
### Based on Input Patterns:
Simple Scenario: Users only input passwords.
Regular Usage: Users input more than just passwords.
Complex Scenario: After entering a password, users switch to other pages and randomly press keys a few times before returning to the current page and pressing Enter.
### Based on Password Conditions:
If users repeatedly use the same password but don't always use an on-screen keyboard.
If users' passwords consist only of numbers.
If users' passwords contain numbers, uppercase and lowercase letters, and symbols.
### Experimental Results:
Variation in user input duration.
Error rate in password input.
User acceptance.
Difficulty of password cracking.
## Extensions:
Search for related research papers on password cracking, which can be combined with shoulder surfing.
Based on the experiment, draw conclusions on techniques to increase password cracking difficulty, such as using combinations of different password forms.

