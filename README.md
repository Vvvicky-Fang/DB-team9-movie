# DB-team9

# Frontend system
**NVM:**
If you have old node or yarn, please uninstall them.

1. `npm uninstall -g yarn`

2. uninstall Node.js![](https://hackmd.io/_uploads/rkB3jcjLh.png)


3. [Download nvm](https://github.com/coreybutler/nvm-windows/releases)
![](https://hackmd.io/_uploads/S1ZQa5jLn.png)
4. install nvm with the exe file
4. install node.js:
`nvm install version`
![](https://hackmd.io/_uploads/BkmRa9iU2.png)

5.open a terminal in Frontend


```
--cd easypc_admin

(please use node10)
--nvm use 16

--npm install

--npm run dev
```
![](https://hackmd.io/_uploads/B1wuk9dv3.jpg)


# Backend system
1. **Open the project**   
![](https://hackmd.io/_uploads/r1u_yio8h.png)

2. **Maven:** 
* Set mvn_resp path in settings.xml
![](https://hackmd.io/_uploads/SyDSMS28h.png)
![](https://hackmd.io/_uploads/rJSUGShU3.png)
![](https://hackmd.io/_uploads/H1z3fShIn.png)  
* Go to IDEA Settings. Search Maven and change the 3 paths of Maven  
 ![](https://hackmd.io/_uploads/rJ430ShU3.png)



3. **Click these two buttons to install packages, it may takes ~20 minutes.**  
![](https://hackmd.io/_uploads/B1ZxRHhUn.png)

4. **Change application.properties for each Service**  
* btw,if your resources folder looks like this: ![](https://hackmd.io/_uploads/BJclJI3Ln.png)  
please mark it's directory as "Resources Root"
![](https://hackmd.io/_uploads/Hyqzy82U3.png)

![](https://hackmd.io/_uploads/SyH1eZiLh.png)


5. Start OmsApplication, AdminApplication and PmsApplication
* if you have this error, please click "JAR manifest".
```
Error running 'xxx':
Command line is too long. Shorten command line for xxx or also for Application default configuration.
```
# MySQL
the sql script of my database is in [script.sql](https://github.com/ChongHao-Qiu/EasyPc-admin-system/tree/main/develop_env/sql/script.sql)
