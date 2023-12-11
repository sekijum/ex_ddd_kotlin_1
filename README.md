# bondagehub-backend

### 起動
```
docker-compose -f compose.develop.yaml up -d
```

### 終了
```
docker-compose -f compose.develop.yaml down
```

### 参考
- https://enterprisecraftsmanship.com/posts/modeling-relationships-in-ddd-way
- https://terasolunaorg.github.io/guideline/public_review/ImplementationAtEachLayer/DomainLayer.html

# コーディングルール

[Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
の思想に則ったパッケージを用意する。

### テーブル定義とドメインオブジェクトの関係性

* ドメイン モデルでその中間テーブルをどのように操作するのでしょうか?
* このガイドラインに従ってください。
* 中間テーブルに関連テーブルへの参照のみが含まれている場合は、そのテーブルのクラスを導入しないでください。
* 中間テーブルに他の情報が含まれている場合は、そのためのクラスを導入してください。


### ControllerとServiceで実装するロジックの責任分界点について
#### ControllerとServiceで実装するロジックは、以下のルールに則って実装することを推奨する。

* クライアントからリクエストされたデータに対する単項目チェック、相関項目チェックはController側で行う。
* Serviceに渡すデータへの変換処理(Bean変換、型変換、形式変換など)は、ServiceではなくController側で行う。
* ビジネスルールに関わる処理はServiceで行う。業務データへのアクセスは、RepositoryまたはO/R Mapperに委譲する。
* ServiceからControllerに返却するデータ（クライアントへレスポンスするデータ）に対する値の変換処理(型変換、形式変換など)は、Serviceではなく、Controller側（Viewクラスなど）で行う。
