robosub-2014
============

#Configuration

###Virtual Environment
The following steps below will setup the python virtual environment.

```bash
virtualenv env
source env/bin/activate
pip install -r requirements.txt
```

###Setting up Dashboard
The dashboard has several dependencies that need to be installed before it can be used.

__development__
```bash
pushd src/auvweb/dashboard
bower install
npm install
popd
```

__production__
```bash
pushd src/auvweb/dashboard
bower install -p
popd
```

###Development Server
Once your virtual environment has been configured and the Dashboard has its required
dependencies the following command will run the auvweb server. AUVWeb should now be running on port `5000`.

__development__
```bash
bin/auvweb
```
