/*
 * @Author: Nathaniel
 * @Date: 2021-02-06 13:41:06
 */
const { app, pool } = require('./connect')
const user = require('./router/user')
app.all('*', (req, res, next) => {
    //这里处理全局拦截，一定要写在最上面
    next()
})
app.get('/', (req, res) => { //首页路由
    res.sendFile(__dirname + '/' + 'index.html')
})
app.all('/', (req, res) => {
    pool.getConnection((err, conn) => {
        res.json({
            type: 'test'
        })
        pool.releaseConnection(conn) // 释放连接池，等待别的连接使用
    })
})
app.use('/user', user)
app.listen(3000, () => {
    console.log('服务启动', 'localhost:3000')
})