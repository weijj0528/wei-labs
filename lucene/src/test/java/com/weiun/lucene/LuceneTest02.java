package com.weiun.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * 查询
 */
public class LuceneTest02 {

    String indexPath = "d:\\.temp\\index";

    /**
     * 查询
     *
     * @throws Exception
     */
    @Test
    public void testSearch() throws Exception {
        // 创建查询解析器,两个参数：默认要查询的字段的名称，分词器
        IKAnalyzer ikAnalyzer = new IKAnalyzer();
        QueryParser parser = new QueryParser("title", ikAnalyzer);
        // MultiFieldQueryParser 多字段的查询解析器
        // MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(new String[]{"id", "title"}, ikAnalyzer);
        // 创建查询对象
        Query query = parser.parse("谷歌");
        search(query);
        // TermQuery
        System.out.println("TermQuery-----------------------------------");
        query = new TermQuery(new Term("title", "谷歌"));
        search(query);
        // WildcardQuery（通配符查询）
        System.out.println("WildcardQuery-----------------------------------");
        query = new WildcardQuery(new Term("title", "*歌*"));
        search(query);
        // FuzzyQuery（模糊查询）
        System.out.println("FuzzyQuery-----------------------------------");
        // 创建模糊查询对象:允许用户输错。但是要求错误的最大编辑距离不能超过2
        // 编辑距离：一个单词到另一个单词最少要修改的次数 facebool --> facebook 需要编辑1次，编辑距离就是1
        query = new FuzzyQuery(new Term("title", "fscevool"));
        // 可以手动指定编辑距离，但是参数必须在0~2之间
        query = new FuzzyQuery(new Term("title", "facevool"), 1);
        search(query, null);
        // NumericRangeQuery（数值范围查询）
        System.out.println("NumericRangeQuery-----------------------------------");
        // 数值范围查询对象，参数：字段名称，最小值、最大值、是否包含最小值、是否包含最大值
        query = NumericRangeQuery.newLongRange("id", 2L, 2L, true, true);
        search(query);
        // BooleanQuery（组合查询）
        System.out.println("BooleanQuery-----------------------------------");
        Query query1 = NumericRangeQuery.newLongRange("id", 1L, 3L, true, true);
        Query query2 = NumericRangeQuery.newLongRange("id", 2L, 4L, true, true);
        // 创建布尔查询的对象
        BooleanQuery booleanQuery = new BooleanQuery();
        // 组合其它查询
        // 布尔查询：布尔查询本身没有查询条件，可以把其它查询通过逻辑运算进行组合！
        //     交集：Occur.MUST + Occur.MUST
        //     并集：Occur.SHOULD + Occur.SHOULD
        //     非  ：Occur.MUST_NOT
        booleanQuery.add(query1, BooleanClause.Occur.MUST_NOT);
        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
        search(booleanQuery);
    }

    private void search(Query query) throws IOException, InvalidTokenOffsetsException {
        search(query, null);
    }

    private void search(Query query, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        // 索引目录对象
        Directory directory = FSDirectory.open(new File(indexPath));
        // 索引读取工具
        IndexReader reader = DirectoryReader.open(directory);
        // 索引搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);

        // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
        // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
        TopDocs topDocs = searcher.search(query, 10);
        // 获取总条数
        System.out.println("本次搜索共找到" + topDocs.totalHits + "条数据");
        // 获取得分文档对象（ScoreDoc）数组.SocreDoc中包含：文档的编号、文档的得分
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            // 取出文档编号
            int docID = scoreDoc.doc;
            // 根据编号去找文档
            Document doc = reader.document(docID);
            System.out.println("id: " + doc.get("id"));
            String title = doc.get("title");
            if (highlighter == null) {
                System.out.println("title: " + title);
            } else {
                // 用高亮工具处理普通的查询结果,参数：分词器，要高亮的字段的名称，高亮字段的原始值
                String hTitle = highlighter.getBestFragment(new IKAnalyzer(), "title", title);
                System.out.println("title: " + hTitle);
            }

            // 取出文档得分
            System.out.println("得分： " + scoreDoc.score);
        }
    }


    // 高亮显示
    @Test
    public void testHighlighter() throws Exception {
        QueryParser parser = new QueryParser("title", new IKAnalyzer());
        Query query = parser.parse("谷歌地图");
        // 格式化器
        Formatter formatter = new SimpleHTMLFormatter("<em>", "</em>");
        QueryScorer scorer = new QueryScorer(query);
        // 准备高亮工具
        Highlighter highlighter = new Highlighter(formatter, scorer);
        search(query, highlighter);

    }

    /**
     * 排序
     *
     * @throws Exception
     */
    @Test
    public void testSortQuery() throws Exception {
        // 目录对象
        Directory directory = FSDirectory.open(new File("indexDir"));
        // 创建读取工具
        IndexReader reader = DirectoryReader.open(directory);
        // 创建搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new QueryParser("title", new IKAnalyzer());
        Query query = parser.parse("谷歌地图");

        // 创建排序对象,需要排序字段SortField，参数：字段的名称、字段的类型、是否反转如果是false，升序。true降序
        Sort sort = new Sort(new SortField("id", SortField.Type.LONG, true));
        // 搜索
        TopDocs topDocs = searcher.search(query, 10, sort);
        System.out.println("本次搜索共" + topDocs.totalHits + "条数据");

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            // 获取文档编号
            int docID = scoreDoc.doc;
            Document doc = reader.document(docID);
            System.out.println("id: " + doc.get("id"));
            System.out.println("title: " + doc.get("title"));
        }
    }

    /**
     * 分页
     *
     * @throws Exception
     */
    @Test
    public void testPageQuery() throws Exception {
        // 实际上Lucene本身不支持分页。因此我们需要自己进行逻辑分页。我们要准备分页参数：
        int pageSize = 2;// 每页条数
        int pageNum = 3;// 当前页码
        int start = (pageNum - 1) * pageSize;// 当前页的起始条数
        int end = start + pageSize;// 当前页的结束条数（不能包含）

        // 目录对象
        Directory directory = FSDirectory.open(new File("indexDir"));
        // 创建读取工具
        IndexReader reader = DirectoryReader.open(directory);
        // 创建搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new QueryParser("title", new IKAnalyzer());
        Query query = parser.parse("谷歌地图");

        // 创建排序对象,需要排序字段SortField，参数：字段的名称、字段的类型、是否反转如果是false，升序。true降序
        Sort sort = new Sort(new SortField("id", SortField.Type.LONG, false));
        // 搜索数据，查询0~end条
        TopDocs topDocs = searcher.search(query, end, sort);
        System.out.println("本次搜索共" + topDocs.totalHits + "条数据");

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (int i = start; i < end; i++) {
            ScoreDoc scoreDoc = scoreDocs[i];
            // 获取文档编号
            int docID = scoreDoc.doc;
            Document doc = reader.document(docID);
            System.out.println("id: " + doc.get("id"));
            System.out.println("title: " + doc.get("title"));
        }
    }
}
