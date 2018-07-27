/* eslint-disable */

const relPath = "/../src/main/resources/static/build";

module.exports = {
	entry: [
		"./src/js/index.js"
	],
	module: {
		rules: [
			{
				test: /\.(js|jsx)$/,
				exclude: /node_modules/,
				use: ["babel-loader"]
			},
			{
				test: /\.css$/,
				use: ["style-loader", "css-loader"]
			},
			{
				test: /\.(scss|sass)$/,
				use: [
					"style-loader",
					"css-loader",
					"sass-loader"
				]
			},
			{
				test: /\.(gif|png|jpe?g|svg|ico)$/i,
				use: [
					"file-loader",
					{
						loader: "image-webpack-loader",
						options: {
							bypassOnDebug: true,
							disable: true,
						},
					}
				],
			},
			{
				test: require.resolve("jquery"),
				use: [
					{ loader: "expose-loader", options: "jQuery" },
					{ loader: "expose-loader", options: "$" }
				]
			}
		]
	},
	node: {
		fs: 'empty'
	},
	resolve: {
		extensions: ["*", ".js", ".jsx"]
	},
	output: {
		path: __dirname + relPath,
		publicPath: "/build/",
		filename: "bundle.js"
	},
	devServer: {
		contentBase: __dirname + "/../src/main/resources/static"
	}
};

/* eslint-enable */
