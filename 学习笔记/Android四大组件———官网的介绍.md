##Android四大组件———官网的介绍


###Activity
>
Activity表示具有用户界面的单一屏幕。例如，电子邮件应用可能具有一个显示新电子邮件列表的 Activity、一个用于撰写电子邮件的 Activity 以及一个用于阅读电子邮件的 Activity。 尽管这些 Activity 通过协作在电子邮件应用中形成了一种具有凝聚力的用户体验，但每一个 Activity 都独立于其他 Activity 而存在。 因此，其他应用可以启动其中任何一个 Activity（如果电子邮件应用允许）。 例如，相机应用可以启动电子邮件应用内用于撰写新电子邮件的 Activity，以便用户共享图片。
Activity 作为 Activity 的子类实现，您可以在Activity开发者指南中了解有关它的更多详情。


###服务
>
服务 是一种在后台运行的组件，用于执行长时间运行的操作或为远程进程执行作业。 服务不提供用户界面。 例如，当用户位于其他应用中时，服务可能在后台播放音乐或者通过网络获取数据，但不会阻断用户与 Activity 的交互。 诸如 Activity 等其他组件可以启动服务，让其运行或与其绑定以便与其进行交互。
服务作为 Service 的子类实现，您可以在服务开发者指南中了解有关它的更多详情。

###内容提供程序
>
内容提供程序 管理一组共享的应用数据。您可以将数据存储在文件系统、SQLite 数据库、Web 上或您的应用可以访问的任何其他永久性存储位置。其他应用可以通过内容提供程序查询数据，甚至修改数据（如果内容提供程序允许）。 例如，Android 系统可提供管理用户联系人信息的内容提供程序。因此，任何具有适当权限的应用都可以查询内容提供程序的某一部分（如 ContactsContract.Data），以读取和写入有关特定人员的信息。
内容提供程序也适用于读取和写入您的应用不共享的私有数据。 例如，记事本示例应用使用内容提供程序来保存笔记。
内容提供程序作为 ContentProvider 的子类实现，并且必须实现让其他应用能够执行事务的一组标准 API。如需了解详细信息，请参阅内容提供程序开发者指南。

###广播接收器
>
广播接收器 是一种用于响应系统范围广播通知的组件。 许多广播都是由系统发起的—例如，通知屏幕已关闭、电池电量不足或已拍摄照片的广播。应用也可以发起广播—例如，通知其他应用某些数据已下载至设备，并且可供其使用。 尽管广播接收器不会显示用户界面，但它们可以创建状态栏通知，在发生广播事件时提醒用户。 但广播接收器更常见的用途只是作为通向其他组件的“通道”，设计用于执行极少量的工作。 例如，它可能会基于事件发起一项服务来执行某项工作。
广播接收器作为 BroadcastReceiver 的子类实现，并且每条广播都作为 Intent 对象进行传递。如需了解详细信息，请参阅 BroadcastReceiver 类。